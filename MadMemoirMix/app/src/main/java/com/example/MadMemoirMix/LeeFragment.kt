package com.example.MadMemoirMix

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import java.util.Timer
import kotlin.concurrent.schedule

private const val ARG_PARAM1 = "state1"
private const val ARG_PARAM2 = "state2"

class LeeFragment : Fragment() {

    // 1은 앞면, 0은 뒷면
    var state1: Int = 1
    var state2: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            state1 = it.getInt("state1")
            state2 = it.getInt("state2")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()

        // show manual dialog
        showManualDialog()
    }

    private fun setOnClickListener() {
        var coin1 = view?.findViewById<android.widget.ImageView>(R.id.coin_image1)
        var coin2 = view?.findViewById<android.widget.ImageView>(R.id.coin_image2)

        var animationCounter = 0

        view?.findViewById<Button>(R.id.toss_button)?.setOnClickListener {
            var animation: android.view.animation.Animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.coin_effect)
            coin1?.startAnimation(animation)
            coin2?.startAnimation(animation)

            var mediaPlayer = android.media.MediaPlayer.create(context, R.raw.coin_effect)
            mediaPlayer.start()

            animationCounter = 0

            Timer().schedule(2200) {
                activity?.runOnUiThread {
                    state1 = (0..1).random()

                    when (state1) {
                        0 -> coin1?.setImageResource(R.drawable.coin_back)
                        1 -> coin1?.setImageResource(R.drawable.coin_front)
                    }

                    animationCounter++

                    if (animationCounter == 2) {
                        showResultDialog()
                    }
                }
            }

            Timer().schedule(2200) {
                activity?.runOnUiThread {
                    state2 = (0..1).random()

                    when (state2) {
                        0 -> coin2?.setImageResource(R.drawable.coin_back)
                        1 -> coin2?.setImageResource(R.drawable.coin_front)
                    }

                    animationCounter++

                    if (animationCounter == 2) {
                        showResultDialog()
                    }
                }
            }
        }

        view?.findViewById<Button>(R.id.back_button)?.setOnClickListener {
            // start fragment transaction
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            // add fragment
            val fragment = CoinFragment()
            val bundle = Bundle()
            bundle.putInt("state1", state1)
            bundle.putInt("state2", state2)
            fragment.arguments = bundle
            transaction.replace(R.id.fragment_container, fragment)

            // commit transaction
            transaction.commit()
        }

        view?.findViewById<ImageButton>(R.id.manual_button)?.setOnClickListener {
            showManualDialog()
        }
    }

    private fun showManualDialog() {
        val manualDialog = LayoutInflater.from(context).inflate(R.layout.dialog_manual_lee, null)
        val manualDialogBuilder = android.app.AlertDialog.Builder(context, R.style.TransparentDialog).setView(manualDialog)
        val manualAlertDialog = manualDialogBuilder.show()

        manualDialog.findViewById<Button>(R.id.close_button)?.setOnClickListener {
            manualAlertDialog.dismiss()
        }
    }

    private fun showResultDialog() {
        //debug toast
//        Toast.makeText(context, "state1: $state1, state2: $state2", Toast.LENGTH_SHORT).show()
        if(state1 == 1 && state2 == 1){
            // delay for 0.5 seconds
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                // execute the code after the delay
                val resultDialog = LayoutInflater.from(context).inflate(R.layout.dialog_result, null)
                val resultDialogBuilder = android.app.AlertDialog.Builder(context, R.style.TransparentDialog).setView(resultDialog)
                val resultAlertDialog = resultDialogBuilder.show()

                resultDialog.findViewById<Button>(R.id.close_button)?.setOnClickListener {
                    resultAlertDialog.dismiss()
                }

                // show for 7 seconds
                Timer().schedule(7000) {
                    activity?.runOnUiThread {
                        resultAlertDialog.dismiss()
                    }
                }
            }, 500)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CoinFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}