package com.example.statusapp.mvvc;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.statusapp.db.model.nodes.NodeWithTags;

import java.util.List;

public class NodeViewModel extends AndroidViewModel {
    private NodeRepository repository;

    public NodeViewModel(@NonNull Application application) {
        super(application);
        this.repository = new NodeRepository(application);
    }

    public void getNodesFromAPIAndStore(){
        repository.ApiCallAndPutInDB();
    }

    public LiveData<List<NodeWithTags>> getNodes(){
        return repository.getAllNodes();
    }
}
