Ext.define('SGCEscalada.model.FaseVelocidadModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'ruta1', type: 'int'},
        {name: 'ruta2', type: 'int'},
        {name: 'idCompetencia', type: 'int'},
        {name: 'idDeportista', type: 'int'},
        {name: 'idCategoria', type: 'int'},
        {name: 'tiempo1', type: 'Ext.data.Types.FLOAT'},
        {name: 'tiempo2', type: 'Ext.data.Types.FLOAT'},
        {name: 'tiempoFinal', type: 'Ext.data.Types.FLOAT'},
        {name: 'mejorTiempo', type: 'Ext.data.Types.FLOAT', convert: function (value, record) {
            var t1 = record.get('tiempo1'),
                t2 = record.get('tiempo2'),
                t;

            if (t1 && !t2) {
                t = t1;
            } else if (!t1 && t2) {
                t = t2;
            } else if (t1 < t2) {
                t = t1;
            } else if (t1 > t2) {
                t = t2;
            }
            else {
                t = t1;
            }
            return t;
        }},
        {name: 'finalizada', type: 'boolean'},

        //Datos del deportista
        {name: 'nombre', type: 'string', convert: function (value, record) {
            return value  + ' ' + record.get('pApellido') + ' ' + record.get('sApellido');
        }},
        {name: 'pApellido', type: 'string'},
        {name: 'sApellido', type: 'string'},
        //Datos de la direccion (Provincia)
        {name: 'provincia', type: 'string'},
        //Datos de la categoria
        {name: 'categoria', type: 'string'},
        {name: 'ranking', type: 'Ext.data.Types.FLOAT'},
        {name: 'punto'}
    ]
});