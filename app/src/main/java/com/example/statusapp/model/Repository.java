package com.example.statusapp.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.statusapp.model.service.Service;
import com.example.statusapp.model.service.ServiceTagsCrossRef;
import com.example.statusapp.model.service.UserTag;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private ServiceDao serviceDao;
    private LiveData<List<Service>> allServices;

    public Repository(Application app){
        Database db = Database.getInstance(app);
        serviceDao = db.dao();
        allServices =  serviceDao.getServices();
    }

    public void insert(Service s){
        new InsertServiceAsyncTask(serviceDao).execute(s);
    }

    public void update(Service s){
        new UpdateServiceAsyncTask(serviceDao).execute(s);
    }

    public void delete(Service s){
        new DeleteServiceAsyncTask(serviceDao).execute(s);
    }

    public void deleteAllServices(Service s){
        new DeleteAllServiceAsyncTask(serviceDao).execute();
    }

    public void insertMultipleServices(ArrayList<Service> services){
        new InsertMultipleServicesTask(serviceDao).execute(services);
    }

    public LiveData<List<Service>> getAllServices() {
        return allServices;
    }

    private static class InsertServiceAsyncTask extends AsyncTask<Service,Void,Void>{

        private ServiceDao serviceDao;

        public InsertServiceAsyncTask(ServiceDao serviceDao) {
            this.serviceDao = serviceDao;
        }

        @Override
        protected Void doInBackground(Service... services) {
            serviceDao.insertService(services[0]);
            ArrayList<UserTag> tags = services[0].getUserTags();
            for(UserTag t : tags){
                serviceDao.insertTag(t);
                serviceDao.insertServiceTagCrossRef(new ServiceTagsCrossRef(services[0].getServiceId(),t.getUserTagId()));
            }
            return null;
        }
    }

    private static class InsertMultipleServicesTask extends AsyncTask<ArrayList<Service>,Void,Void>{

        private ServiceDao serviceDao;

        public InsertMultipleServicesTask(ServiceDao serviceDao) {
            this.serviceDao = serviceDao;
        }

        @Override
        protected Void doInBackground(ArrayList<Service>... arrayLists) {
            for(Service s: arrayLists[0]){
                serviceDao.insertService(s);
            }
            return null;
        }
    }

    private static class UpdateServiceAsyncTask extends AsyncTask<Service,Void,Void>{

        private ServiceDao serviceDao;

        public UpdateServiceAsyncTask(ServiceDao serviceDao) {
            this.serviceDao = serviceDao;
        }

        @Override
        protected Void doInBackground(Service... services) {
            serviceDao.update(services[0]);
            return null;
        }
    }

    private static class DeleteServiceAsyncTask extends AsyncTask<Service,Void,Void>{

        private ServiceDao serviceDao;

        public DeleteServiceAsyncTask(ServiceDao serviceDao) {
            this.serviceDao = serviceDao;
        }

        @Override
        protected Void doInBackground(Service... services) {
            serviceDao.delete(services[0]);
            return null;
        }
    }

    private static class DeleteAllServiceAsyncTask extends AsyncTask<Void,Void,Void>{

        private ServiceDao serviceDao;

        public DeleteAllServiceAsyncTask(ServiceDao serviceDao) {
            this.serviceDao = serviceDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            serviceDao.deleteAllServices();
            return null;
        }
    }
}
