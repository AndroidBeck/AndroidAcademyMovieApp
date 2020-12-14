package ru.aevd.androidacademymovieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentMoviesList: Fragment() {

    private var clickListener: TransactionsFragmentClicks? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            setOnClickListener {clickListener?.showDetails()}
        }
    }

    fun setClickListener(listener: TransactionsFragmentClicks?) {
        clickListener = listener
    }

    interface TransactionsFragmentClicks {
        fun showDetails()
    }

//    companion object {
//        const val FRAGMENT_TAG = "Fragment MoviesList"
//
//        fun newInstance(): FragmentMoviesList {
//            val fragment = FragmentMoviesList()
//            val args = Bundle()
//            fragment.arguments = args
//            return fragment
//        }
//    }

}