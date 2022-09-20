package com.android.tiktaktoe.ui.game

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.android.tiktaktoe.R
import com.android.tiktaktoe.databinding.FragmentGameBinding
import com.android.tiktaktoe.domain.model.Field
import com.android.tiktaktoe.domain.model.Player

class GameFragment : Fragment(R.layout.fragment_game) {

    private val args by navArgs<GameFragmentArgs>()
    private val viewModel by viewModels<GameViewModel>()
    private var binding: FragmentGameBinding? = null
    private val adapter = FieldAdapter {
        viewModel.onMoveDone(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setupStartPlayer(args.player)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGameBinding.bind(view).apply { setUpRecyclerView() }

        viewModel.getCurrentTurnData().observe(viewLifecycleOwner) { player: Player? ->
            // TODO: here should be final text only, logic should be moved out
            when (player) {
                Player.X -> binding?.tvCurrentTurn?.text = getString(R.string.x_turn)
                Player.O -> binding?.tvCurrentTurn?.text = getString(R.string.o_turn)
                else -> Unit
            }
        }

        viewModel.getFieldData().observe(viewLifecycleOwner) {
            adapter.setList(it)
        }

        viewModel.getWinnerData().observe(viewLifecycleOwner) {
            AlertDialog.Builder(requireContext())
                // TODO: same here, we should get text instead of player
                .setTitle(getString(R.string.winner_is_template, it.toString()))
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok) { _, _ -> findNavController().popBackStack() }
                .show()
        }

        viewModel.getIsDrawData().observe(viewLifecycleOwner) {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.draw))
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok) { _, _ -> findNavController().popBackStack() }
                .show()
        }
    }

    private fun FragmentGameBinding.setUpRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(requireContext(), Field.FIELD_SIZE)
        recyclerView.adapter = adapter
        val drawable = ColorDrawable(Color.GRAY)

        val horizontalDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)
        horizontalDecoration.setDrawable(drawable)
        recyclerView.addItemDecoration(horizontalDecoration)

        val verticalDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        verticalDecoration.setDrawable(drawable)
        recyclerView.addItemDecoration(verticalDecoration)
    }
}