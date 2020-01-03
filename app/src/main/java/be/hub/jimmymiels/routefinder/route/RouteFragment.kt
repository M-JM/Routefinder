package be.hub.jimmymiels.routefinder.route


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import be.hub.jimmymiels.routefinder.databinding.FragmentRouteBinding
import be.hub.jimmymiels.routefinder.R
import be.hub.jimmymiels.routefinder.title.TitleViewModel
import kotlinx.android.synthetic.main.fragment_route.*


class RouteFragment : Fragment() {

    private lateinit var viewModel: TitleViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment

        val binding: FragmentRouteBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_route,
            container,
            false
        )
        viewModel = ViewModelProviders.of(activity!!).get(TitleViewModel::class.java)
        binding.titleViewModel = viewModel
        binding.lifecycleOwner = this


        viewModel.searchTerm.observe(this, object : Observer<Any> {
            override fun onChanged(t: Any?) {
                textView.text = t!!.toString()
                Toast.makeText(context,viewModel.sendIntent.toString(), Toast.LENGTH_SHORT).show()
            }


        })

        binding.newsearchButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_routeFragment_to_titleFragment))
        binding.mapButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_routeFragment_to_mapFragment))

        setHasOptionsMenu(true)


        return binding.root

    }

    // Inflate the Menu xml
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
            R.id.share -> startActivity(viewModel.getShareIntent())
        }
        return super.onOptionsItemSelected(item)
    }
}




