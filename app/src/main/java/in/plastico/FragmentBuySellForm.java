package in.plastico;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaredrummler.materialspinner.MaterialSpinner;

public class FragmentBuySellForm extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy_sell_form_temp, container, false);

        MaterialSpinner feedType = (MaterialSpinner) view.findViewById(R.id.feedType);
        MaterialSpinner productName = (MaterialSpinner)view.findViewById(R.id.productName);
        MaterialSpinner productQty = (MaterialSpinner)view.findViewById(R.id.productQty);

        feedType.setItems("Select one","Buy","Sell");
        productName.setItems("Item 1","Item 2","Item 3");
        productQty.setItems("1","2","3","4");

        feedType.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
