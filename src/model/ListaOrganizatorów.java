package model;

import java.util.ArrayList;

public class ListaOrganizatorów {
    private int id;
    private ArrayList<Organizator> listaOrganizatorów = new ArrayList<Organizator>();


    public ListaOrganizatorów(int _id)
    {
        id = _id;
    }

    public ArrayList<Organizator> getListaOrganizatorów()
    {
        if(listaOrganizatorów.isEmpty())
            return null;
        else
            return this.listaOrganizatorów;
    }

    public boolean czyPusta()
    {
        return listaOrganizatorów.isEmpty();
    }

    public int rejestracja(String _login, String _hasło)
    {
        for(int i = 0; i < listaOrganizatorów.size(); i++)
        {
            if (listaOrganizatorów.get(i).getLogin().equals(_login))
            {
                return  404;
            }
        }
        listaOrganizatorów.add(new Organizator(_login,_hasło));
        return 0;
    }

    public Organizator login(String _login, String _hasło)
    {

        for(int i = 0; i < listaOrganizatorów.size(); i++)
        {
            if (listaOrganizatorów.get(i).getLogin().equals(_login) && listaOrganizatorów.get(i).getHasło().equals(_hasło))
            {
              return  listaOrganizatorów.get(i).getThis();
            }
        }
        return null; // jeżeli nie ma takiego obiektu wskazuje na null, w gui przy operacjach zawsze pierwszym warunkiem musi być if(lista.login(login,hasło) != null). Jeśli to nie będzie sprawdzone,
        //i okaże się, że login wskazuje na null, to będzie sypało wyjątkami
    }











}