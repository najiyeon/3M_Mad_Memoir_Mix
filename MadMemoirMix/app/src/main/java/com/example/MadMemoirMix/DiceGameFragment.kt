package com.example.MadMemoirMix

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import java.util.Timer
import kotlin.concurrent.schedule

private const val ARG_PARAM1 = "state"

class DiceGameFragment : Fragment() {
    private var state: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            state = it.getInt("state")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dicegame, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()

        // show manual dialog
        showManualDialog()
    }

    private fun setOnClickListener() {
        var dice = view?.findViewById<android.widget.ImageView>(R.id.dice_image)

        var animationCounter = 0

        view?.findViewById<Button>(R.id.roll_button)?.setOnClickListener {
            var animation: android.view.animation.Animation =
                android.view.animation.AnimationUtils.loadAnimation(context, R.anim.dice_effect)
            dice?.startAnimation(animation)

            var mediaPlayer = android.media.MediaPlayer.create(context, R.raw.dice_effect)
            mediaPlayer.start()

            animationCounter = 0

            Timer().schedule(2200) {
                activity?.runOnUiThread {
                    state = (1..6).random()

                    when (state) {
                        1 -> dice?.setImageResource(R.drawable.dice_1)
                        2 -> dice?.setImageResource(R.drawable.dice_2)
                        3 -> dice?.setImageResource(R.drawable.dice_3)
                        4 -> dice?.setImageResource(R.drawable.dice_4)
                        5 -> dice?.setImageResource(R.drawable.dice_5)
                        6 -> dice?.setImageResource(R.drawable.dice_6)
                    }

                    animationCounter++

                    if (animationCounter == 1){
                        showResultDialog()
                    }
                }
            }
        }

        view?.findViewById<Button>(R.id.back_button)?.setOnClickListener {
            // start fragment transaction
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            // add fragment
            val fragment = DiceFragment()
            val bundle = Bundle()
            bundle.putInt("state", state)
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
        val manualDialog = LayoutInflater.from(context).inflate(R.layout.dialog_manual_dicegame, null)
        val manualDialogBuilder = android.app.AlertDialog.Builder(context, R.style.TransparentDialog).setView(manualDialog)
        val manualAlertDialog = manualDialogBuilder.show()

        manualDialog.findViewById<Button>(R.id.close_button)?.setOnClickListener {
            manualAlertDialog.dismiss()
        }
    }

    private fun showResultDialog(){
        // debug toast
//        Toast.makeText(context, "state: $state", Toast.LENGTH_SHORT).show()

        // delay for 0.5 seconds
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            // execute the code after the delay
            val resultDialog = LayoutInflater.from(context).inflate(R.layout.dialog_result_dicegame, null)
            val resultDialogBuilder = android.app.AlertDialog.Builder(context, R.style.TransparentDialog).setView(resultDialog)
            val resultAlertDialog = resultDialogBuilder.show()

            // find the TextView within the inflated view
            val count = resultDialog.findViewById<TextView>(R.id.result_content2)

            Log.d("Debug", "Count TextView: $count")

            when (state) {
                1 -> count?.text = "1 잔"
                2 -> count?.text = "2 잔"
                3 -> count?.text = "3 잔"
                4 -> count?.text = "4 잔"
                5 -> count?.text = "5 잔"
                6 -> count?.text = "6 잔"
            }

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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DiceGameFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, state)
                }
            }
    }
}