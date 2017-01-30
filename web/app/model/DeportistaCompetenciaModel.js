Ext.define('SGCEscalada.model.DeportistaCompetenciaModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'iddeportista', type: 'int'},
        {name: 'idProvincia', type: 'int'},
        {name: 'federacion', type: 'string'},
        {name: 'nombre', type: 'string', convert: function (value, record) {
            return value + ' ' + record.get('pApellido') + ' ' + record.get('sApellido');
        }},
        {name: 'idSexo', type: 'int'},
        {name: 'idTipoSangre', type: 'int'},
        {name: 'pApellido', type: 'string'},
        {name: 'sApellido', type: 'string'},
        {name: 'provincia', type: 'string'}
    ]
});