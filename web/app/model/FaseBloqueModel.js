Ext.define('SGCEscalada.model.FaseBloqueModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'idCompetencia', type: 'int'},
        {name: 'idDeportista', type: 'int'},
        {name: 'idCategoria', type: 'int'},
        {name: 't1', type: 'int'},
        {name: 't2', type: 'int'},
        {name: 't3', type: 'int'},
        {name: 't4', type: 'int'},
        {name: 't5', type: 'int'},
        {name: 'b1', type: 'int'},
        {name: 'b2', type: 'int'},
        {name: 'b3', type: 'int'},
        {name: 'b4', type: 'int'},
        {name: 'b5', type: 'int'},
        {name: 'totalTop', type: 'int', convert: function (value, record) {
            return (record.get('t1') ? 1 : 0) +
            (record.get('t2') ? 1 : 0) +
            (record.get('t3') ? 1 : 0) +
            (record.get('t4') ? 1 : 0) +
            (record.get('t5') ? 1 : 0);
        }},
        {name: 'intentosTop', type: 'int', convert: function (value, record) {
            return record.get('t1') +
            record.get('t2') +
            record.get('t3') +
            record.get('t4') +
            record.get('t5');
        }},
        {name: 'totalBonos', type: 'int', convert: function (value, record) {
            return (record.get('b1') ? 1 : 0) +
            (record.get('b2') ? 1 : 0) +
            (record.get('b3') ? 1 : 0) +
            (record.get('b4') ? 1 : 0) +
            (record.get('b5') ? 1 : 0);
        }},
        {name: 'intentosBonos', type: 'int', convert: function (value, record) {
            return record.get('b1') +
            record.get('b2') +
            record.get('b3') +
            record.get('b4') +
            record.get('b5');
        }},

        //Auxiliares calculo ranking
        {name: 'primeraColumnaTop_terceraColumnaBono', type: 'string', convert: function (value, record) {
            return record.get('t1') +record.get('b3');
        }},
        {name: 'segundaColumnaBono_cuartaColumnaBono', type: 'string', convert: function (value, record) {
            return record.get('b2') + record.get('b4');
        }},

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