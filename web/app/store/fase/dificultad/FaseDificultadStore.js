Ext.define('SGCEscalada.store.fase.dificultad.FaseDificultadStore', {
    extend: 'Ext.data.Store',
    model:'SGCEscalada.model.FaseDificultadModel',
    proxy: {
        type: 'ajax',
        url: 'listarFaseDificultad.htm'
    },
    sorters: ['puntos', 'presa'],
    autoLoad: false
});