package com.example.statusapp.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.statusapp.API.models.node.Node;
import com.example.statusapp.db.model.ServiceEntity;
import com.example.statusapp.db.model.ServiceTagCrossRef;
import com.example.statusapp.db.model.UserTagEntity;
import com.example.statusapp.API.models.Tag;
import com.example.statusapp.API.models.service.Service;
import com.example.statusapp.db.model.nodes.NodeEntity;
import com.example.statusapp.db.model.nodes.NodeTagCrossRef;
import com.example.statusapp.db.model.nodes.TechTagEntity;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {ServiceEntity.class, UserTagEntity.class, ServiceTagCrossRef.class,
        NodeEntity.class, NodeTagCrossRef.class, TechTagEntity.class},version = 1)
public abstract class StatusappDB extends RoomDatabase {
    private static StatusappDB instance;
    public abstract ServiceDao serviceDao();
    public abstract NodeDao nodeDao();

    public static synchronized StatusappDB getInstance(Context ctx){
        if(instance==null){
            instance = Room.databaseBuilder(ctx.getApplicationContext(),StatusappDB.class,"statusapp_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    /**
     * SERVICES
     */
    public void insertServices(ArrayList<Service> services){
        new InsertServicesWithTagsInDb(this.serviceDao()).execute(services);
    }

    public void deleteAllServices(){
        new DeleteAllServicesAsyncTask(this.serviceDao()).execute();
    }

    private class InsertServicesWithTagsInDb extends AsyncTask<ArrayList<Service>,Void,Void>{

        private ServiceDao dao;

        public InsertServicesWithTagsInDb(ServiceDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ArrayList<Service>... arrayLists) {
            for(Service s :arrayLists[0]){
                List<Tag> tags = s.getTags();
                dao.insertServices(s.toEntity());
                dao.deleteServiceTagCrossRefs(s.getId());
                for(Tag t: tags){
                    dao.insertUserTags(t.toUserTag());
                    dao.insertServiceTagCrossRef(new ServiceTagCrossRef(s.getId(),t.getId()));
                }
            }
            return null;
        }
    }

    private class DeleteAllServicesAsyncTask extends AsyncTask<Void,Void,Void>{

        private ServiceDao dao;

        public DeleteAllServicesAsyncTask(ServiceDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllServiceTagCrossRefs();
            dao.deleteAllUserTags();
            dao.deleteAllServices();
            return null;
        }
    }

    /**
     * NODES
     */

    public void insertNodes(ArrayList<Node> nodes){
        new InsertNodesWithTagsInDb(this.nodeDao()).execute(nodes);
    }

    public void deleteAllNodes(){
        new DeleteAllNodesAsyncTask(this.nodeDao()).execute();
    }

    private class InsertNodesWithTagsInDb extends AsyncTask<ArrayList<Node>,Void,Void>{

        private NodeDao dao;

        public InsertNodesWithTagsInDb(NodeDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ArrayList<Node>... arrayLists) {
            if(arrayLists[0].size()>0){
                for(Node s :arrayLists[0]){
                    List<Tag> tags = s.getTags();
                    dao.insertNodes(s.toEntity());
                    dao.deleteNodeTagCrossRefs(s.getId());
                    for(Tag t: tags){
                        dao.insertTechTags(t.toTechTag());
                        dao.insertNodeTagCrossRef(new NodeTagCrossRef(s.getId(),t.getId()));
                    }
                }
            }
            return null;
        }
    }

    private class DeleteAllNodesAsyncTask extends AsyncTask<Void,Void,Void>{

        private NodeDao dao;

        public DeleteAllNodesAsyncTask(NodeDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllNodeTagCrossRefs();
            dao.deleteAllTechTags();
            dao.deleteAllNodes();
            return null;
        }
    }

}
