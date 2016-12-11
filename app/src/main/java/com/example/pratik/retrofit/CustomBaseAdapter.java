package com.example.pratik.retrofit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pratik.retrofit.Pojo.UserDetail;

import java.util.List;

import static android.R.attr.name;

/**
 * Created by Pratik on 29-Nov-16.
 */

public class CustomBaseAdapter extends BaseAdapter {
    Activity context;
    List<UserDetail> userDetailList;

    public CustomBaseAdapter(Activity context, List<UserDetail> items) {
        this.context = context;
        this.userDetailList = items;
    }

    /*private view holder class*/
    private class ViewHolder {
        TextView tv_id;
        TextView tv_name;
        TextView tv_username;
        TextView tv_email;
        TextView tv_suite, tv_street, tv_city, tv_zipcode;
        TextView tv_phone;
        TextView tv_website;
        TextView tv_lat, tv_lng;
        TextView tv_namecompany, tv_catchPhrase, tv_bs;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_username = (TextView) convertView.findViewById(R.id.tv_username);
            holder.tv_email = (TextView) convertView.findViewById(R.id.tv_email);
            holder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            holder.tv_website = (TextView) convertView.findViewById(R.id.tv_website);
            holder.tv_suite = (TextView) convertView.findViewById(R.id.tv_suite);
            holder.tv_street = (TextView) convertView.findViewById(R.id.tv_street);
            holder.tv_city = (TextView) convertView.findViewById(R.id.tv_city);
            holder.tv_zipcode = (TextView) convertView.findViewById(R.id.tv_zipcode);
            holder.tv_namecompany = (TextView) convertView.findViewById(R.id.tv_companyname);
            holder.tv_catchPhrase = (TextView) convertView.findViewById(R.id.tv_catchPhrase);
            holder.tv_bs = (TextView) convertView.findViewById(R.id.tv_bs);
            holder.tv_lng = (TextView) convertView.findViewById(R.id.tv_lng);
            holder.tv_lat = (TextView) convertView.findViewById(R.id.tv_lat);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final UserDetail userDetail = (UserDetail) getItem(position);

        holder.tv_id.setText(userDetail.getId().toString());
        holder.tv_name.setText(userDetail.getName());
        holder.tv_username.setText(userDetail.getUsername());
        holder.tv_email.setText(userDetail.getEmail());
        holder.tv_street.setText(userDetail.getAddress().getStreet());
        holder.tv_suite.setText(userDetail.getAddress().getSuite());
        holder.tv_city.setText(userDetail.getAddress().getCity());
        holder.tv_zipcode.setText(userDetail.getAddress().getZipcode());
        holder.tv_lat.setText(userDetail.getAddress().getGeo().getLat());
        holder.tv_lng.setText(userDetail.getAddress().getGeo().getLng());
        holder.tv_phone.setText(userDetail.getPhone());
        holder.tv_website.setText(userDetail.getWebsite());
        holder.tv_namecompany.setText(userDetail.getCompany().getName());
        holder.tv_catchPhrase.setText(userDetail.getCompany().getCatchPhrase());
        holder.tv_bs.setText(userDetail.getCompany().getBs());


//                holder.tv_lat.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String s1 = userDetail.getAddress().getGeo().getLat();
//                        String s2 = userDetail.getAddress().getGeo().getLng();
//                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=-" + s1 + "," + s2 + " (" + userDetail.getName() + ")"));
//                        context.startActivity(intent);
//                        Toast.makeText(context, "lat"+s1+"lng"+s2, Toast.LENGTH_SHORT).show();
//                    }
//                });



//        for (int i = 0; i <userDetailList.get(position).getAddress().size() ; i++) {
//            holder.tv_street.setText(userDetailList.get(position).getAddress().get(i).getStreet());
//            holder.tv_suite.setText(userDetailList.get(position).getAddress().get(i).getSuite());
//            holder.tv_city.setText(userDetailList.get(position).getAddress().get(i).getCity());
//            holder.tv_zipcode.setText(userDetailList.get(position).getAddress().get(i).getZipcode());
//
//            for (int j = 0; j <userDetailList.get(position).getAddress().get(i).getGeo().size() ; j++) {
//                holder.tv_lat.setText(userDetailList.get(position).getAddress().get(i).getGeo().get(j).getLat());
//                holder.tv_lng.setText(userDetailList.get(position).getAddress().get(i).getGeo().get(j).getLng());
//            }
//
//        }
//
//        for (int i = 0; i <userDetailList.get(position).getCompany().size() ; i++) {
//            holder.tv_namecompany.setText(userDetailList.get(position).getCompany().get(i).getName());
//            holder.tv_catchPhrase.setText(userDetailList.get(position).getCompany().get(i).getCatchPhrase());
//            holder.tv_bs.setText(userDetailList.get(position).getCompany().get(i).getBs());
//        }


        return convertView;
    }

    @Override
    public int getCount() {
        return userDetailList.size();
    }

    @Override
    public Object getItem(int position) {
        return userDetailList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return userDetailList.indexOf(getItem(position));
    }
}
