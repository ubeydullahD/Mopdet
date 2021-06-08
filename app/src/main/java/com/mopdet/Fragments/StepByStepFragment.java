package com.mopdet.Fragments;

public class StepByStepFragment extends Fragment {

    private TextView textView,textViewOf;
    private RadioButton radiobir,radioiki,radiouc,radiodort;
    private RadioGroup radioGroup;
    private  Test test;
    private Button buttonNext;
    private int count = 0;
    public int TotalPuan;
    private boolean butonControl = false;

    public StepByStepFragment(Test test) {
        this.test = test;
    }

    public View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.new_survey_card_tasarim,container,false);
        buttonNext = mView.findViewById(R.id.button2);
        radioGroup  = mView.findViewById(R.id.rGroup);
        mView.setFocusableInTouchMode(true);
        mView.requestFocus();
        mView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.i("tag", "keyCode: " + i);
                if( i == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP) {
                    Log.e("tag", "onKey Back listener is working!!!");

                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    Intent intent = new Intent(getActivity(), TestsActivity.class);
                                    startActivity(intent);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("If you come back, your changes will not be saved. Are you sure ? ")
                            .setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                    return true;
                }
                return false;
            }
        });


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if(butonControl){
                    if(count==test.getData().get(0).getMobdetTest().getQuestions().size()){
                        // if(count==2){
                        Fragment fragment = new ScoreFragment(
                                TotalPuan,
                                test.getData().get(0).getMobdetTest().getTestName(),
                                test.getData().get(0).getMobdetTest().getId()
                        );
                        getFragmentManager().beginTransaction().replace(R.id.fragment_tutucu,fragment).commit();
                    }else{
                        if(count==test.getData().get(0).getMobdetTest().getQuestions().size()-1){
                            buttonNext.setBackground(getResources().getDrawable(R.color.colorPrimaryDark));
                            buttonNext.setText("FINISH");
                        }
                        radioGroup.clearCheck();
                        preperaView(count,mView);
                        butonControl =false;
                    }

                }else {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),"Please answer completely",Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(Color.GRAY);
                    snackbar.show();
                }


            }
        });
        return preperaView(count,mView);
    }

    public View preperaView(int count, View mView){
        textViewOf = mView.findViewById(R.id.textView5);
        textView =  mView.findViewById(R.id.textView4);
        radiobir = mView.findViewById(R.id.radioButton);
        radioiki = mView.findViewById(R.id.radioButton3);
        radiouc = mView.findViewById(R.id.radioButton4);
        radiodort  = mView.findViewById(R.id.radioButton5);
        textView.setText((count+1)+"."+test.getData().get(0).getMobdetTest().getQuestions().get(count).getmDQuestion().getQuestion());
        textViewOf.setText((count+1)+" of "+test.getData().get(0).getMobdetTest().getQuestions().size());




        textView.setText((count+1)+"."+test.getData().get(0).getMobdetTest().getQuestions().get(count).getmDQuestion().getQuestion());
        radiobir.setText(test.getData().get(0).getMobdetTest().getQuestions().get(count).getAnswer().get(0).getAnswer());
        radioiki.setText(test.getData().get(0).getMobdetTest().getQuestions().get(count).getAnswer().get(1).getAnswer());
        radiouc.setText(test.getData().get(0).getMobdetTest().getQuestions().get(count).getAnswer().get(2).getAnswer());
        radiodort.setText(test.getData().get(0).getMobdetTest().getQuestions().get(count).getAnswer().get(3).getAnswer());
        radiobir.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                butonControl=true;

                if(b){
                    TotalPuan = TotalPuan+4;
                    Log.e("sa",Integer.toString(TotalPuan));
                }else{
                    TotalPuan = TotalPuan-4;
                    Log.e("sa",Integer.toString(TotalPuan));
                }
            }
        });
        radioiki.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                butonControl=true;
                if(b){
                    TotalPuan = TotalPuan+3;
                    Log.e("sa",Integer.toString(TotalPuan));
                }else{
                    TotalPuan = TotalPuan-3;
                    Log.e("sa",Integer.toString(TotalPuan));
                }
            }
        });
        radiouc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                butonControl=true;
                if(b){
                    TotalPuan = TotalPuan+2;
                    Log.e("sa",Integer.toString(TotalPuan));
                }else{
                    TotalPuan = TotalPuan-2;
                    Log.e("sa",Integer.toString(TotalPuan));
                }
            }
        });
        radiodort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                butonControl=true;
                if(b){
                    TotalPuan = TotalPuan+1;
                    Log.e("sa",Integer.toString(TotalPuan));
                }else{
                    TotalPuan = TotalPuan-1;
                    Log.e("sa",Integer.toString(TotalPuan));
                }
            }
        });
        this.count = this.count+1;
        return mView;
    }
}
