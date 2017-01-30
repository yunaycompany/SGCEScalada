Ext.define('SGCEscalada.view.media.CargarImagen', {
    extend: 'Ext.form.field.File',
    name: 'file',
    alias: 'widget.cargarImagen',
    msgTaet: 'side',
    editable: false,
    allowBlank: false,
    margin: 5,

    initComponent: function () {
        this.emptyText = 'Seleccione la imagen...';
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