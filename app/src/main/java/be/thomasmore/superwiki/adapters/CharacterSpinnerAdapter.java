package be.thomasmore.superwiki.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import be.thomasmore.superwiki.R;
import be.thomasmore.superwiki.classes.Character;

public class CharacterSpinnerAdapter extends ArrayAdapter<Character> {

    private final Context context;
    private final List<Character> values;
    public CharacterSpinnerAdapter(Context context, List<Character> values ) {
        super(context, R.layout.characterspinneritem,  values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.characterspinneritem, parent, false);

        final TextView textViewName = (TextView) rowView.findViewById(R.id.characterspinnername);

        textViewName.setText(values.get(position).getNaam());

        return rowView;
    }
}
