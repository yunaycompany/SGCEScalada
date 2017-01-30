Ext.define('User', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'idParroquia', type: 'int'},
        {name: 'idLugar', type: 'int'},
        {name: 'lugar', type: 'string'}
    ]
});