package pl.edu.uwr.kalkulatorstezenchemicznych

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object Compounds {
    val compounds: MutableList<Compound> = mutableListOf(
        Compound("Tlenek sodu","Na2O",61.97),
        Compound("Tlenek potasu","K2O",94.2),
        Compound("Tlenek magnezu","MgO",40.30),
    )
}

class HomeFragment : Fragment() {

    private var mContext: Context? = null

    private val compoundsList: MutableList<Compound> = Compounds.compounds

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.compoundRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CompoundAdapter(requireContext(), compoundsList)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}