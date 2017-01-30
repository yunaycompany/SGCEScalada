Ext.define('SGCEscalada.view.media.CargarVideo', {
    extend: 'Ext.form.field.File',
    name: 'file',
    alias: 'widget.cargarVideo',
    msgTaet: 'side',
    editable: false,
    allowBlank: false,
    margin: 5,

    initComponent: function () {
        this.emptyText = 'Seleccione el video...';
        this.buttonText = 'Buscar';
        this.validateOnChange = true;
        this.buttonConfig = {
            //icon: Replica.perfil.dirImg + + 'cancelar.png',
            frame: true
        };
        //this.validator = function (value) {
        //    if (value) {
        //        if (value.split('.').pop() != 'zip') {
        //            return /*idioma.lbMsgIncorrectoTextBold+*/idioma.msgErrorArchivoRpl;
        //        }
        //        else {
        //            return true;
        //        }
        //    }
        //};
        this.callParent(arguments);
    }
});