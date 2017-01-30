Ext.define('SGCEscalada.model.FaseDificultadModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'idCompetencia', type: 'int'},
        {name: 'idDeportista', type: 'int'},
        {name: 'idCategoria', type: 'int'},
        {name: 'presa', type: 'int'},
        {name: 'puntos', type: 'Ext.data.Types.FLOAT', convert: function (value, record) {
            var puntos = record.get('puntos');
            if(record.get('agarre')=='+'){
                puntos = record.get('presa') + 0.2;
            }
            else{
                puntos = record.get('presa');
            }
            return puntos;
        }
        },
        {name: 'agarre', type: 'string'},
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