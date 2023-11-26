package kz.jusan.homework6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val ARG_NEWS = "news"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var news: News? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            news = @Suppress("DEPRECATION")it.getSerializable(ARG_NEWS) as News
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvAuthor: TextView = view.findViewById(R.id.tv_author)
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvDate: TextView = view.findViewById(R.id.tv_date)

        tvAuthor.text = news?.author?: ""
        tvTitle.text = news?.title?: ""
        tvDate.text = news?.date?: ""
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(news: News) =
                NewsDetailsFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_NEWS, news)
                    }
                }
    }
}