package com.sujata.virginmoneydemo.ui.peoples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sujata.virginmoneydemo.databinding.FragmentPeoplesBinding
import com.sujata.virginmoneydemo.framework.ViewModelFactory
import com.sujata.virginmoneydemo.framework.api.Status

class PeoplesFragment : Fragment(), PeoplesRecyclerAdapter.ItemClickListener {

    private var _binding: FragmentPeoplesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val peoplesViewModel: PeoplesViewModel by activityViewModels {
        ViewModelFactory(requireActivity().application)
    }

    private lateinit var adapter: PeoplesRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = PeoplesRecyclerAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPeoplesBinding.inflate(inflater, container, false)
        setUpViews()

        peoplesViewModel.peoplesDataResponse.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { peoplesData ->
                            adapter.setListData(peoplesData)
                            binding.recyclerview.adapter = adapter
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, resource.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                         binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }


        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        peoplesViewModel.fetchPeoplesData()
    }

    private fun setUpViews() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerview.addItemDecoration(
            DividerItemDecoration(
                context,
                layoutManager.orientation
            )
        )
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.setHasFixedSize(true)

        with(binding.swiperefresh) {
            setOnRefreshListener {
                binding.progressBar.visibility = View.GONE
                peoplesViewModel.fetchPeoplesData()
                isRefreshing = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(position: Int) {

        //Toast.makeText(context, "Message Clicked", Toast.LENGTH_LONG).show()
        val action=PeoplesFragmentDirections.actionPeoplesFragmentToPeoplesDetailsFragment(position)
        findNavController().navigate(action)
    }
}