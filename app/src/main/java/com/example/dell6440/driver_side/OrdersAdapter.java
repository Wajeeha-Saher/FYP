package com.example.dell6440.driver_side;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class OrdersAdapter extends ArrayAdapter<OrderDTO> implements View.OnClickListener {
    private final Context context;
    private final OrderDTO[] orders;

    private static class ViewHolder {
        TextView pickUpLocation;
        TextView dropOfLocation;
        TextView loadType;
        TextView weight;
    }


    public OrdersAdapter(Context context, OrderDTO[] orders) {
        super(context, R.layout.orders_listview, orders);
        this.context = context;
        this.orders = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position
        OrderDTO dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.orders_listview, parent, false);

            viewHolder.pickUpLocation = (TextView) convertView.findViewById(R.id.pickUpLocation);
            viewHolder.dropOfLocation = (TextView) convertView.findViewById(R.id.dropOfLocation);
            viewHolder.loadType = (TextView) convertView.findViewById(R.id.loadType);
            viewHolder.weight = (TextView) convertView.findViewById(R.id.weight);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        /*Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;*/

        viewHolder.pickUpLocation.setText(orders[position].PickupLocation);
        viewHolder.dropOfLocation.setText(orders[position].DropOffLocation);
        viewHolder.loadType.setText(orders[position].LoadType);
        viewHolder.weight.setText(orders[position].Weight);
        /*viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);*/
        // Return the completed view to render on screen
        return convertView;


      /*  LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.orders_listview, parent, false);

        TextView pickUpLocation = (TextView) rowView.findViewById(R.id.pickUpLocation);
        TextView dropOfLocation = (TextView) rowView.findViewById(R.id.dropOfLocation);
        TextView loadType = (TextView) rowView.findViewById(R.id.loadType);
        TextView weight = (TextView) rowView.findViewById(R.id.weight);


        pickUpLocation.setText(orders[position].PickupLocation);
        dropOfLocation.setText(orders[position].DropOffLocation);
        loadType.setText(orders[position].LoadType);
        weight.setText(orders[position].Weight);

        return rowView;*/
    }


    @Override
    public void onClick(View view) {

        int position = (Integer) view.getTag();
        Object object = getItem(position);
        OrderDTO order = (OrderDTO) object;

       /* switch (v.getId())
        {
            case R.id.item_info:
                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }*/
    }


}
