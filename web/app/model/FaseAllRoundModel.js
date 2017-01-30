Ext.define('SGCEscalada.model.FaseAllRoundModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'idCompetencia', type: 'int'},
        {name: 'idDeportista', type: 'int'},
        {name: 'idCategoria', type: 'int'},
        {name: 'rankingV', type: 'int'},
        {name: 'puntosV', type: 'int'},
        {name: 'rankingD', type: 'int'},
        {name: 'puntosD', type: 'int'},
        {name: 'rankingB', type: 'int'},
        {name: 'puntosB', type: 'int'},
        {name: 'totalPuntos', type: 'Ext.data.Types.FLOAT', convert: function (value, record) {
            return record.get('puntosD') + record.get('puntosV') + record.get('puntosB');
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