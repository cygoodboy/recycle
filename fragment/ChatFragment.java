package com.bignerdranch.android.recycle.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.android.recycle.R;
import com.bignerdranch.android.recycle.entity.ChatMSG;
import com.bignerdranch.android.recycle.entity.ChatMSGLab;
import com.bignerdranch.android.recycle.entity.CommonViewHolder;
import com.bignerdranch.android.recycle.widget.TopView;

import java.util.List;

/**
 * Created by asus on 2017/11/23.
 */

public class ChatFragment extends Fragment {
    private View view;
    private static final String KEY_CONTACT_NAME = "contactName";

    private ChatAdapter mChatAdapter;
    private String mContactName;

    private TopView mTopView;
    private RecyclerView mChatRv;
    private EditText mChatContentEt;
    private Button mSendBtn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        init();
        listener();
        adapter();
        return view;
    }
    public static ChatFragment newInstance(String contactName) {
        ChatFragment chatFragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_CONTACT_NAME, contactName);
        chatFragment.setArguments(args);
        return chatFragment;
    }
    public void init() {
        mContactName = getArguments().getString(KEY_CONTACT_NAME);
        mTopView = (TopView) view.findViewById(R.id.topView);
        mTopView.setText("消息", mContactName, null);
        mTopView.showRightAsImageView();
        mChatRv = (RecyclerView) view.findViewById(R.id.chat_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(true);
        mChatRv.setLayoutManager(linearLayoutManager);
        mChatContentEt = (EditText) view.findViewById(R.id.chatContent_editText);
        mSendBtn = (Button) view.findViewById(R.id.send_button);
    }

    public void listener() {
        mTopView.setupListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        },null);
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mChatContentEt.getText().toString().equals("")) {
                    ChatMSG chatMSG = ChatMSG.get();
                    chatMSG.setMsg(mChatContentEt.getText().toString());
                    chatMSG.setFromMe(true);
                    mChatContentEt.setText(null);
                    ChatMSGLab.touch().add(chatMSG);
                    updateChatMessage();
                }
            }
        });
    }

    public void adapter() {
        mChatAdapter = new ChatAdapter(ChatMSGLab.touch().get());
        mChatRv.setAdapter(mChatAdapter);
    }

    public void updateChatMessage() {
        mChatAdapter.setChatMSGList(ChatMSGLab.touch().get());
        mChatAdapter.notifyDataSetChanged();
        mChatRv.scrollToPosition(mChatAdapter.getLastPosition());
    }

    private class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {

        private List<ChatMSG> mChatMSGList;

        private ChatAdapter(List<ChatMSG> chatMSGList) {
            mChatMSGList = chatMSGList;
        }

        @Override
        public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_for_chat, parent, false);
            return new ChatViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ChatViewHolder holder, int position) {
            holder.bindChatMSG(mChatMSGList.get(position));
        }

        @Override
        public int getItemCount() {
            return mChatMSGList.size();
        }

        private void setChatMSGList(List<ChatMSG> chatMSGList) {
            mChatMSGList = chatMSGList;
        }

        private int getLastPosition() {
            return mChatMSGList.size() - 1;
        }
    }

    private class ChatViewHolder extends RecyclerView.ViewHolder {

        private View mItemView;
        private LinearLayout mFriendView;
        private LinearLayout mMyView;
        private TextView mFriendTv;
        private TextView mMyTv;

        private ChatViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            init();
        }

        private void bindChatMSG(ChatMSG chatMSG) {
            if (chatMSG.getFromMe()) {
                mMyView.setVisibility(View.VISIBLE);
                mFriendView.setVisibility(View.GONE);
                mMyTv.setText(chatMSG.getMsg());
            } else {
                mFriendView.setVisibility(View.VISIBLE);
                mMyView.setVisibility(View.GONE);
                mFriendTv.setText(chatMSG.getMsg());
            }
        }

        private void init() {
            mFriendView = getView(R.id.friendView_linearLayout);
            mMyView = getView(R.id.myView_linearLayout);
            mFriendTv = getView(R.id.friendMessage_textView);
            mMyTv = getView(R.id.myMessage_textView);
        }

        @SuppressWarnings("unchecked")
        private <T extends View> T getView(int id) {
            return CommonViewHolder.get(mItemView, id);
        }
    }
}
