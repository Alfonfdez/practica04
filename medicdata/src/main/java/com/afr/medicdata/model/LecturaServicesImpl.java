package com.afr.medicdata.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LecturaServicesImpl implements LecturaServices{

    private static final Map<Integer, Lectura> LECTURAS;

    private static final LecturaServicesImpl INSTANCE = new LecturaServicesImpl();

    static{
        LECTURAS = new TreeMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date fecha0 = null;
        Date fecha1 = null;
        Date fecha2 = null;
        Date fecha3 = null;
        Date fecha4 = null;
        Date fecha5 = null;
        Date fecha6 = null;
        Date fecha7 = null;
        Date fecha8 = null;
        Date fecha9 = null;


        try {
            fecha0 = sdf.parse("01/01/2019 15:00:00");
            fecha1 = sdf.parse("02/01/2019 18:59:25");
            fecha2 = sdf.parse("03/01/2019 10:05:01");
            fecha3 = sdf.parse("04/01/2019 21:35:30");
            fecha4 = sdf.parse("05/01/2019 16:15:42");
            fecha5 = sdf.parse("06/01/2019 17:00:08");
            fecha6 = sdf.parse("07/01/2019 09:35:15");
            fecha7 = sdf.parse("08/01/2019 13:48:50");
            fecha8 = sdf.parse("09/01/2019 22:20:30");
            fecha9 = sdf.parse("10/01/2019 21:05:20");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Lectura l0 = new Lectura(fecha0, 85.4, 91.2, 105.3,  41.3818,2.1685);
        Lectura l1 = new Lectura(fecha1, 85.9, 90.2, 102.5,  41.3818,2.1685);
        Lectura l2 = new Lectura(fecha2, 86.0, 92.5, 104.0,  41.3818,2.1685);
        Lectura l3 = new Lectura(fecha3, 85.2, 90.6, 106.9,  41.3818,2.1685);
        Lectura l4 = new Lectura(fecha4, 84.8, 90.3, 106.8,  41.3818,2.1685);
        Lectura l5 = new Lectura(fecha5, 84.9, 90.9, 103.4,  41.3818,2.1685);
        Lectura l6 = new Lectura(fecha6, 85.3, 92.0, 107.6,  41.3818,2.1685);
        Lectura l7 = new Lectura(fecha7, 85.3, 95.2, 108.2,  41.3818,2.1685);
        Lectura l8 = new Lectura(fecha8, 85.7, 96.7, 106.7,  41.3818,2.1685);
        Lectura l9 = new Lectura(fecha9, 85.9, 95.3, 105.0,  41.3818,2.1685);

        l0.setCodigo(100);
        l1.setCodigo(101);
        l2.setCodigo(102);
        l3.setCodigo(103);
        l4.setCodigo(104);
        l5.setCodigo(105);
        l6.setCodigo(106);
        l7.setCodigo(107);
        l8.setCodigo(108);
        l9.setCodigo(109);

        LECTURAS.put(l0.getCodigo(),l0);
        LECTURAS.put(l1.getCodigo(),l1);
        LECTURAS.put(l2.getCodigo(),l2);
        LECTURAS.put(l3.getCodigo(),l3);
        LECTURAS.put(l4.getCodigo(),l4);
        LECTURAS.put(l5.getCodigo(),l5);
        LECTURAS.put(l6.getCodigo(),l6);
        LECTURAS.put(l7.getCodigo(),l7);
        LECTURAS.put(l8.getCodigo(),l8);
        LECTURAS.put(l9.getCodigo(),l9);

    }

    private LecturaServicesImpl(){

    }

    public static LecturaServicesImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Lectura create(Lectura lectura) {

        //Hemos de calcular el nuevo código...
        //Casteamos LECTURAS
        Integer maxCode = ((TreeMap<Integer, Lectura>)LECTURAS).lastKey();
        Integer newCode = maxCode++;

        lectura.setCodigo(newCode);

        return LECTURAS.put(lectura.getCodigo(), lectura);
    }

    @Override
    public Lectura read(Integer codigo) {
        return LECTURAS.get(codigo);
    }

    @Override
    public Lectura update(Lectura lectura) {

        //Me tiene que llegar una lectura con código existente
        if(lectura.getCodigo() == null || !LECTURAS.containsKey(lectura.getCodigo())){
            throw new IllegalArgumentException("Sólo se pueden actualizar lecturas existentes");
        }

        return LECTURAS.put(lectura.getCodigo(), lectura);
    }

    @Override
    public boolean delete(Integer codigo) {

        Lectura lectura = LECTURAS.remove (codigo);

        return lectura == null ? false: true;
    }

    @Override
    public List<Lectura> getAll() {
        return new ArrayList<Lectura>(LECTURAS.values());
    }

    @Override
    public List<Lectura> getBetweenDates(Date fecha1, Date fecha2) {
        List<Lectura> lecturas = new ArrayList<>();

        for(Lectura lectura : getAll()){

            Date fechaHora = lectura.getFechaHora();

            if(fechaHora.after(fecha1) && fechaHora.before(fecha2)){
                lecturas.add(lectura);
            }
        }

        return lecturas;
    }
}
