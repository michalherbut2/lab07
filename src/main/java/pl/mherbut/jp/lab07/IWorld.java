package pl.mherbut.jp.lab07;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IWorld extends Remote {
    public int register(ICallback ic, String role) throws RemoteException;
    public boolean unregister(int id) throws RemoteException;
    public void move(int id) throws RemoteException;
    public void seed(int id) throws RemoteException;
    public void harvest(int id, List<Integer> l) throws RemoteException;
}
