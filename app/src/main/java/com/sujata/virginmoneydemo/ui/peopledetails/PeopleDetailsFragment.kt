package com.sujata.virginmoneydemo.ui.peopledetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.sujata.virginmoneydemo.R

import com.sujata.virginmoneydemo.databinding.FragmentPeoplesDetailsBinding
import com.sujata.virginmoneydemo.ui.peoples.PeoplesViewModel

class PeopleDetailsFragment : Fragment() {

    private var _binding: FragmentPeoplesDetailsBinding? = null

    private val viewModel: PeoplesViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeoplesDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val args: PeopleDetailsFragmentArgs by navArgs()
        viewModel.peoplesDataResponse.value?.run {
            data?.get(args.position)?.run {
                Glide.with(binding.root)
                    .load(this.avatar)
                    .placeholder(R.drawable.no_image)
                    .into(binding.newsImageIV)
                binding.authorNameTV.text=firstName+" "+lastName
                binding.publishedDateTV.text=email
                
            }
        }
/*
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)
*/


        /*val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}