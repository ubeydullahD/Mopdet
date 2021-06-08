package com.mopdet.Core;

public class TestResultHistoryAdapter extends   RecyclerView.Adapter<TestResultHistoryAdapter.CardViewTasatimNesneleriniTutucu> {

    private Context context;
    private Response<TestResultHistory> testResultHistory;
    private View  mView;
    private FragmentManager fm ;
    private  Activity activity;

    private TextView textView13, textView16, textView18;

    public TestResultHistoryAdapter(Context context, Response<TestResultHistory> testResultHistory, View mView, FragmentManager fm, Activity activity) {
        this.context = context;
        this.testResultHistory = testResultHistory;
        this.mView = mView;
        this.fm = fm;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CardViewTasatimNesneleriniTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.test_result_history_card,parent,false);
        return new TestResultHistoryAdapter.CardViewTasatimNesneleriniTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTasatimNesneleriniTutucu holder, int position) {

        textView13.setText(testResultHistory.body().getTestHistory().get(position).getTestName());
        textView16.setText(testResultHistory.body().getTestHistory().get(position).getScore().toString());
        textView18.setText(testResultHistory.body().getTestHistory().get(position).getTestSonucText());
    }

    @Override
    public int getItemCount() {
        return testResultHistory.body().getTestHistory().size();
    }


    public class CardViewTasatimNesneleriniTutucu extends RecyclerView.ViewHolder {


        public CardViewTasatimNesneleriniTutucu(@NonNull View itemView) {
            super(itemView);
            textView13 = itemView.findViewById(R.id.textView13);
            textView16 = itemView.findViewById(R.id.textView16);
            textView18 = itemView.findViewById(R.id.textView18);

            mView.setFocusableInTouchMode(true);
            mView.requestFocus();
            mView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if( i == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP) {

                        Intent intent  =  new Intent(activity ,TestsActivity.class);
                        activity.startActivity(intent);

                    }
                    return false;
                }
            });


        }
    }

}
