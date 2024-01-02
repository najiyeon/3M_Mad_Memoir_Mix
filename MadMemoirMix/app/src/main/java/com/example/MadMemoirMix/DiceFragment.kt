package com.example.MadMemoirMix

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import java.util.Timer
import kotlin.concurrent.schedule

private const val ARG_PARAM1 = "state1"
private const val ARG_PARAM2 = "state2"
class DiceFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_dice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        var dice1 = view?.findViewById<android.widget.ImageView>(R.id.dice_image1)
        var dice2 = view?.findViewById<android.widget.ImageView>(R.id.dice_image2)

        view?.findViewById<Button>(R.id.roll_button)?.setOnClickListener {
            var animation: android.view.animation.Animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.dice_effect)
            dice1?.startAnimation(animation)
            dice2?.startAnimation(animation)

            var mediaPlayer = android.media.MediaPlayer.create(context, R.raw.dice_effect)
            mediaPlayer.start()

            Timer().schedule(2200) {
                activity?.runOnUiThread {
                    state1 = (1..6).random()

                    when (state1) {
                        1 -> dice1?.setImageResource(R.drawable.dice_1)
                        2 -> dice1?.setImageResource(R.drawable.dice_2)
                        3 -> dice1?.setImageResource(R.drawable.dice_3)
                        4 -> dice1?.setImageResource(R.drawable.dice_4)
                        5 -> dice1?.setImageResource(R.drawable.dice_5)
                        6 -> dice1?.setImageResource(R.drawable.dice_6)
                    }
                }
            }

            Timer().schedule(2200) {
                activity?.runOnUiThread {
                    state2 = (1..6).random()

                    when (state2) {
                        1 -> dice2?.setImageResource(R.drawable.dice_1)
                        2 -> dice2?.setImageResource(R.drawable.dice_2)
                        3 -> dice2?.setImageResource(R.drawable.dice_3)
                        4 -> dice2?.setImageResource(R.drawable.dice_4)
                        5 -> dice2?.setImageResource(R.drawable.dice_5)
                        6 -> dice2?.setImageResource(R.drawable.dice_6)
                    }
                }
            }
        }

        view?.findViewById<Button>(R.id.sell_button)?.setOnClickListener {
            // 프래그먼트 트랜잭션 시작
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            // 프래그먼트 추가
            val fragment = DiceGameFragment()
            val bundle = Bundle()
            bundle.putInt("state1", state1)
            bundle.putInt("state2", state2)
            fragment.arguments = bundle
            transaction.replace(R.id.fragment_container, fragment)

            // 트랜잭션 커밋
            transaction.commit()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DiceFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}