package be.thomasmore.superwiki.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.List;

import be.thomasmore.superwiki.R;
import be.thomasmore.superwiki.classes.Character;

public class CharacterAdapter extends ArrayAdapter<Character> {

    private final Context context;
    private final List<Character> values;
    public CharacterAdapter(Context context, List<Character> values ) {
        super(context, R.layout.characterlistviewitem,  values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.characterlistviewitem, parent, false);

        final ImageView imageCharacter = (ImageView) rowView.findViewById(R.id.characterlistimage);
        final TextView textViewName = (TextView) rowView.findViewById(R.id.characterlistname);

        Picasso.get().load(values.get(position).getImageUrl()).into(imageCharacter);
        textViewName.setText(values.get(position).getNaam());

        return rowView;

    }
}
