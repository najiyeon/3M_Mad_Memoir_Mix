package com.example.MadMemoirMix

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import java.util.Timer
import kotlin.concurrent.schedule

private const val ARG_PARAM1 = "state"
class CoinFragment : Fragment() {

    // 1은 앞면, 0은 뒷면
    var state: Int = 1

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
        return inflater.inflate(R.layout.fragment_coin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        var coin = view?.findViewById<android.widget.ImageView>(R.id.coin_image)

        view?.findViewById<Button>(R.id.toss_button)?.setOnClickListener {
            var animation: android.view.animation.Animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.coin_effect)
            coin?.startAnimation(animation)

            var mediaPlayer = android.media.MediaPlayer.create(context, R.raw.coin_effect)
            mediaPlayer.start()

            Timer().schedule(2200) {
                activity?.runOnUiThread {
                    state = (0..1).random()

                    when (state) {
                        0 -> coin?.setImageResource(R.drawable.coin_back)
                        1 -> coin?.setImageResource(R.drawable.coin_front)
                    }
                }
            }
        }

        view?.findViewById<Button>(R.id.lee_button)?.setOnClickListener {
            // 프래그먼트 트랜잭션 시작
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            // 프래그먼트 추가
            val fragment = LeeFragment()
            val bundle = Bundle()
            bundle.putInt("state", state)
            fragment.arguments = bundle
            transaction.replace(R.id.fragment_container, fragment)

            // 트랜잭션 커밋
            transaction.commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            CoinFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}