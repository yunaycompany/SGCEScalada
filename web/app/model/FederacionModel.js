Ext.define('SGCEscalada.model.FederacionModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'idFederacion', type: 'int'},
        {name: 'idProvincia', type: 'int'},
        {name: 'federacion', type: 'string'}
    ]
});