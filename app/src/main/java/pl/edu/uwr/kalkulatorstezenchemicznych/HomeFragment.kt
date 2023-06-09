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

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.compoundRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CompoundAdapter(requireContext(), compoundsList)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getTasksList(requireContext())

        val name = arguments?.getString("name")
        val symbol = arguments?.getString("symbol")
        val moleMass = arguments?.getDouble("moleMass")

        if(name!=null && symbol!=null && moleMass!=null) {
            if(!compoundsList.contains(Compound(name,symbol,moleMass))) {
                (recyclerView.adapter as CompoundAdapter).addToList(name,symbol,moleMass)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    fun saveTaskList(context: Context) {
        (recyclerView.adapter as CompoundAdapter).saveCompoundList(context)
    }

    fun getTasksList(context: Context) {
        (recyclerView.adapter as CompoundAdapter).getCompoundList(context)
    }
}