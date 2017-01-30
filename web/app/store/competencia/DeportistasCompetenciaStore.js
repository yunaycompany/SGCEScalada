Ext.define('SGCEscalada.store.competencia.DeportistasCompetenciaStore', {
    extend: 'Ext.data.Store',
    model: 'SGCEscalada.model.DeportistaCompetenciaModel',
    proxy: {
        type: 'ajax',
        url: 'listarDeportistas.htm'
    },
    autoLoad: false
});