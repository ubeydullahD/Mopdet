package com.mopdet.Fragments;

public class TestsFragment extends Fragment {

    View mView;

    private RecyclerView rvTests ;
    private TestsAdapter testsAdapter;
    SharedPreferences mPrefs;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.tests_fragment,container,false);

        mPrefs = this.getActivity().getSharedPreferences("Mobdet", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("Tests", "");
        BaseTest baseTest = gson.fromJson(json, BaseTest.class);

        rvTests = mView.findViewById(R.id.tests_rv);
        rvTests.setHasFixedSize(true);
        rvTests.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        testsAdapter = new TestsAdapter(getActivity().getBaseContext(),baseTest.getData().size(),baseTest,getActivity().getSupportFragmentManager(),getActivity());
        rvTests.setAdapter(testsAdapter);
        return   mView;
    }
}
