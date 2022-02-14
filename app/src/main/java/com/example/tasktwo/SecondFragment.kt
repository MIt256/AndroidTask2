package com.example.tasktwo

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.Navigation
import com.example.tasktwo.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    lateinit var bindingSF: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingSF = FragmentSecondBinding.inflate(layoutInflater)
        with(bindingSF) {
            ViewCompat.setTransitionName(elementImage, arguments?.getString("imageId"))
            ViewCompat.setTransitionName(titleTextView, arguments?.getString("title"))
            ViewCompat.setTransitionName(descriptionTextView, arguments?.getString("description"))
        }

        bindingSF.apply {
            elementImage.setImageResource(arguments!!.getInt("imageId"))
            titleTextView.text = arguments?.getString("title")
            descriptionTextView.text = arguments?.getString("description")
        }

        bindingSF.textBack.setOnClickListener {
            Navigation.findNavController(bindingSF.root)
                .navigate(R.id.action_secondFragment_to_firstFragment)
        }

        bindingSF.buttonForClose.setOnClickListener {
            getActivity()?.finish()
        }
        return bindingSF.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val animation = TransitionInflater.from(context).inflateTransition(
                android.R.transition.move
            )
            sharedElementEnterTransition = animation
            sharedElementReturnTransition = animation
        }
    }
}