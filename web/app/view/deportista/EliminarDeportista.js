Ext.define('SGCEscalada.view.deportista.EliminarDeportista', {
    extend: 'Ext.window.Window',
    alias: 'widget.eliminarDeportista',
    autoShow: true,
    title: 'Eliminar deportista',
    resizable: false,
    layout: 'fit',
    width: 624,
    modal: true,

    initComponent: function () {
        var me = this;

        me.grdDeportistaCompetenciaAdicionarDeportistaCompetencia = Ext.widget('grdDeportistaCompetencia',{
            padding: '5 1',
            margin: '4',
            height: 550,
            eliminar:true
        });
        me.grdDeportistaCompetenciaAdicionarDeportistaCompetencia.columns[0].text = 'Eliminar';
        me.grdDeportistaCompetenciaAdicionarDeportistaCompetencia.columns[0].dataIndex = 'eliminar';
        me.btnAceptarEliminarDeportista = Ext.create('Ext.button.Button', {
            formBind: true,
            text: 'Eliminar',
            action: 'aceptarEliminarDeportista'
        });

        me.btnCancelarAdicionarDeportistaCompetencia = Ext.create('Ext.button.Button', {
            text: 'Cancelar',
            action: 'cancelarEliminarDeportistas'
        });

        me.frmAdicionarDeportistaCompetencia = Ext.create('Ext.form.Panel',{
            border:false,
            items:[me.fsNombreCompetenciaAdicionarDeportistaCompetencia, me.grdDeportistaCompetenciaAdicionarDeportistaCompetencia],
            buttons: [me.btnCancelarAdicionarDeportistaCompetencia, me.btnAceptarEliminarDeportista]
        });

        me.items = [me.frmAdicionarDeportistaCompetencia];

        me.callParent(arguments);
    }
});