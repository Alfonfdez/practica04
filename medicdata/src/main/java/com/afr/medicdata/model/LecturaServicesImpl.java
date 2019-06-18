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

        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");

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

        Date hora0 = null;
        Date hora1 = null;
        Date hora2 = null;
        Date hora3 = null;
        Date hora4 = null;
        Date hora5 = null;
        Date hora6 = null;
        Date hora7 = null;
        Date hora8 = null;
        Date hora9 = null;


        try {
            fecha0 = sdfFecha.parse("01/01/2019");
            fecha1 = sdfFecha.parse("02/01/2019");
            fecha2 = sdfFecha.parse("03/01/2019");
            fecha3 = sdfFecha.parse("04/01/2019");
            fecha4 = sdfFecha.parse("05/01/2019");
            fecha5 = sdfFecha.parse("06/01/2019");
            fecha6 = sdfFecha.parse("07/01/2019");
            fecha7 = sdfFecha.parse("08/01/2019");
            fecha8 = sdfFecha.parse("09/01/2019");
            fecha9 = sdfFecha.parse("10/01/2019");

            hora0 = sdfHora.parse("15:00");
            hora1 = sdfHora.parse("18:59");
            hora2 = sdfHora.parse("10:05");
            hora3 = sdfHora.parse("21:35");
            hora4 = sdfHora.parse("16:15");
            hora5 = sdfHora.parse("17:00");
            hora6 = sdfHora.parse("09:35");
            hora7 = sdfHora.parse("13:49");
            hora8 = sdfHora.parse("22:20");
            hora9 = sdfHora.parse("21:05");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Lectura l0 = new Lectura(fecha0, hora0, 85.4, 91.2, 105.3,  41.3818,2.1685);
        Lectura l1 = new Lectura(fecha1, hora1,85.9, 90.2, 102.5,  41.3818,2.1685);
        Lectura l2 = new Lectura(fecha2, hora2,86.0, 92.5, 104.0,  41.3818,2.1685);
        Lectura l3 = new Lectura(fecha3, hora3, 85.2, 90.6, 106.9,  41.3818,2.1685);
        Lectura l4 = new Lectura(fecha4, hora4, 84.8, 90.3, 106.8,  41.3818,2.1685);
        Lectura l5 = new Lectura(fecha5, hora5, 84.9, 90.9, 103.4,  41.3818,2.1685);
        Lectura l6 = new Lectura(fecha6, hora6, 85.3, 92.0, 107.6,  41.3818,2.1685);
        Lectura l7 = new Lectura(fecha7, hora7, 85.3, 95.2, 108.2,  41.3818,2.1685);
        Lectura l8 = new Lectura(fecha8, hora8, 85.7, 96.7, 106.7,  41.3818,2.1685);
        Lectura l9 = new Lectura(fecha9, hora9, 85.9, 95.3, 105.0,  41.3818,2.1685);

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

            //Date fechaHora = lectura.getFechaHora();
            Date fecha = lectura.getFecha();

            if(fecha.after(fecha1) && fecha.before(fecha2)){
                lecturas.add(lectura);
            }
        }

        return lecturas;
    }
}
