Ext.define('SGCEscalada.view.fase.FrmFases',{
    extend: 'Ext.form.Panel',
    alias: 'widget.frmFases',
    border:0,
    layout:'fit',

    initComponent:function(){
        var me = this;

        me.tbar = [
            Ext.widget('cbxCompetencias',{labelAlign:'top',action:'cbxCompetenciasFrmFases'}),
            Ext.widget('cbxFases', {labelAlign: 'top',width:200, disabled: true}),
            Ext.widget('cbxCategorias', {labelAlign: 'top', disabled: true}),'->',
            {
                xtype:'button',
                text: 'Exportar a pdf',
                action:'exportarAPdf'
            },
            {
                xtype:'button',
                text: 'Exportar a xls',
                action:'exportarAXls'
            }
        ];

        if (!cliente) {
            me.btnAceptarGuardarFase = Ext.create('Ext.button.Button', {
                formBind: true,
                text: 'Guardar',
                action: 'aceptarGuardarFase'
            });
            me.buttons = [me.btnAceptarGuardarFase];
        }
        me.callParent(arguments);
    }
});