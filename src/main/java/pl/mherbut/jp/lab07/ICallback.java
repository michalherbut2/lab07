package pl.mherbut.jp.lab07;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICallback extends Remote {
    void response(Action action, List<Integer> data) throws RemoteException;
}
