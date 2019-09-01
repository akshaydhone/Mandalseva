package com.mandalseva.notification.Interface;


import com.mandalseva.notification.Model.Clients;

import java.util.List;

public interface IFirebaseLoadDone {


    void onFirebaseLoadSuccess(List<Clients> clientsList);
    void onFirebaseLoadFail(String message);
}
