Ext.define('SGCEscalada.view.competencia.EliminarCompetencia', {
    extend: 'Ext.window.Window',
    alias: 'widget.eliminarCompetencia',
    autoShow: true,
    title: 'Eliminar competencia',
    resizable: false,
    layout: 'fit',
    //height: 350,
    width: 424,
    modal: true,

    initComponent: function () {
        var me = this;

        me.cbxCompetencia = Ext.widget('cbxCompetencias', {
            //width: 368,
            labelAlign: 'top'
        });

        me.fsNombreCompetencia = Ext.create('Ext.form.FieldSet',{
            title: 'Nombre competencia:',
            layout: 'fit',
            padding: 5,
            margin: 5,
            items:[me.cbxCompetencia]
        });

        me.btnAceptarAdicionarCompetencia = Ext.create('Ext.button.Button', {
            formBind: true,
            text: 'Eliminar',
            action: 'aceptarEliminarCompetencia'
        });

        me.btnCancelarAdicionarCompetencia = Ext.create('Ext.button.Button', {
            text: 'Cancelar',
            action: 'cancelarAdicionarCompetencia'
        });

        me.frmAdicionarCompetencia = Ext.create('Ext.form.Panel',{
            border:0,
            items:[me.fsDireccionCompetencia, me.fsNombreCompetencia],
            buttons:[me.btnCancelarAdicionarCompetencia, me.btnAceptarAdicionarCompetencia]
        });

        me.items = [me.frmAdicionarCompetencia];
        me.callParent(arguments);
    }
});