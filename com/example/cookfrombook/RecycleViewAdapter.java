package com.example.cookfrombook;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyHolder> {

    private Context mContext;
    private List<Recipes> mData;
    public static boolean lotti_status=false;

    public RecycleViewAdapter(Context mContext, List<Recipes> mData){
        this.mContext = mContext;
        this.mData = mData;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_recipe, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.recipeTitle.setText(mData.get(i).getRecipeName());

        myHolder.img_recipe_thumbnail.setImageResource(mData.get(i).getThumbnail());

        myHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, com.example.cookfrombook.RecipeActivity.class);

                intent.putExtra("Name", mData.get(i).getRecipeName());
                intent.putExtra("Ingredients", mData.get(i).getRecipeIngredients());
                intent.putExtra("MethodTitle", mData.get(i).getRecipeMethodTitle());
                intent.putExtra("Recipe", mData.get(i).getRecipe());

                mContext.startActivity(intent);

                myHolder.lottieAnimationView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(lotti_status==true){
                            myHolder.lottieAnimationView.setProgress(0);
                            lotti_status = false;
                        }else{
                            myHolder.lottieAnimationView.setMinAndMaxProgress(0.0f, 0.5f);
                            myHolder.lottieAnimationView.playAnimation();
                            lotti_status = true;
                        }
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView recipeTitle;
        CardView cardView;
        ImageView img_recipe_thumbnail;
        LottieAnimationView lottieAnimationView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            recipeTitle = (TextView)itemView.findViewById(R.id.text_recipe);
            img_recipe_thumbnail = (ImageView)itemView.findViewById(R.id.recipe_img_id);
            cardView = (CardView)itemView.findViewById(R.id.cardview_id);
            lottieAnimationView = (LottieAnimationView)itemView.findViewById(R.id.lottifav);
        }
    }
}
