package pl.edu.uwr.kalkulatorstezenchemicznych

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object addCompounds {
    val compounds: MutableList<Compound> = mutableListOf(
        Compound("Tlenek baru","BaO",153.33),
        Compound("Tlenek glinu","AlO",101.96),
        Compound("Tlenek cyny","SnO",134.71),
        Compound("Tlenek ołowiu","PbO",223.2),
        Compound("Tlenek cynku","ZnO",81.38),
    )
}

class AddNew : Fragment() { private val wordList by lazy { List(50) { "word $it" } }

    private var mContext: Context? = null

    private val compoundsList: MutableList<Compound> = addCompounds.compounds

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.add_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.compoundRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = AddCompoundAdapter(requireContext(), compoundsList)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}