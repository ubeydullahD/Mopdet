package com.mopdet.Fragments;

public class ScoreFragment extends Fragment {

    private  View mView;
    private int total,testId;
    private String testName;

    private TextView rank, testNametext,explain;

    public RetrofitProcess retrofitProcess;

    SharedPreferences mPrefs ;

    public ScoreFragment(int total , String testName,int testId) {
        this.total = total;
        this.testName = testName;
        this.testId = testId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.score,container,false);
        rank = mView.findViewById(R.id.textView6);
        explain = mView.findViewById(R.id.textView8);
        rank.setText(String.valueOf(total));
        testNametext = mView.findViewById(R.id.textView7);
        testNametext.setText(testName);

        mView.setFocusableInTouchMode(true);
        mView.requestFocus();
        mView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if( i == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP) {
                    Fragment fragment  = new TestsFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();

                    return true;
                }
                return false;
            }
        });

        createViewWithExplain();
        return mView;
    }

    public void createViewWithExplain(){


        Gson gson = new Gson();
        mPrefs = getActivity().getSharedPreferences("Mobdet",Context.MODE_PRIVATE);
        String json = mPrefs.getString("User", "");
        LoginUser user = gson.fromJson(json,LoginUser.class);

        JSONObject item = new JSONObject();
        try {
            item.put("test_id",this.testId);
            item.put("score",this.total);
            item.put("user_id",user.getUser().getId());
            item.put("test_name",this.testName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        retrofitProcess = ApiUtils.saveResult();
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(item).toString());
        retrofitProcess.saveResult(body).enqueue(new Callback<TestResult>() {
            @Override
            public void onResponse(Call<TestResult> call, Response<TestResult> response) {
                explain.setText(response.body().getResult());
            }

            @Override
            public void onFailure(Call<TestResult> call, Throwable t) {
                int b = 0;
            }
        });
    }

}