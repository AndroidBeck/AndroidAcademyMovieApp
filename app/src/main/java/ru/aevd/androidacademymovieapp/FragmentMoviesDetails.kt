package ru.aevd.androidacademymovieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentMoviesDetails: Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

//    companion object {
//        const val FRAGMENT_TAG = "Fragment MoviesDetails"
//
//        fun newInstance(): FragmentMoviesDetails {
//            val fragment = FragmentMoviesDetails()
//            val args = Bundle()
//            fragment.arguments = args
//            return fragment
//        }
//    }

}
