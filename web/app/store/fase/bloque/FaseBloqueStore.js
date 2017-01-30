Ext.define('SGCEscalada.store.fase.bloque.FaseBloqueStore', {
    extend: 'Ext.data.Store',
    model:'SGCEscalada.model.FaseBloqueModel',
    proxy: {
        type: 'ajax',
        url: 'listarFaseBloque.htm'
    },
    sorters: ['totalTop','primeraColumnaTop_terceraColumnaBono', 'segundaColumnaBono_cuartaColumnaBono'],
    autoLoad: false
});