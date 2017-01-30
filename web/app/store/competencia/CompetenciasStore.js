Ext.define('SGCEscalada.store.competencia.CompetenciasStore', {
    extend: 'Ext.data.Store',
    fields: ['idCompetencia', 'idLugar', 'fecha','nombre'],
    proxy: {
        type: 'ajax',
        url: 'listarCompetenciasNoFinalizadas.htm'
    },
    autoLoad: false
});