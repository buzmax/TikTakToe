package com.android.tiktaktoe.ui.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.tiktaktoe.R
import com.android.tiktaktoe.databinding.FragmentMenuBinding
import com.android.tiktaktoe.domain.model.Player

class MenuFragment : Fragment(R.layout.fragment_menu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentMenuBinding.bind(view).apply {
            btnPlayAsX.setOnClickListener {
                findNavController().navigate(MenuFragmentDirections.toGameFragment(Player.X))
            }
            btnPlayAsO.setOnClickListener {
                findNavController().navigate(MenuFragmentDirections.toGameFragment(Player.O))
            }
        }
    }
}