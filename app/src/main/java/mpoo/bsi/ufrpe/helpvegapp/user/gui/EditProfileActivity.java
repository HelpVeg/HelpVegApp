package mpoo.bsi.ufrpe.helpvegapp.user.gui;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import mpoo.bsi.ufrpe.helpvegapp.R;
import mpoo.bsi.ufrpe.helpvegapp.user.business.UserBusiness;
import mpoo.bsi.ufrpe.helpvegapp.infra.Session;
/**
 * <h1>EditProfileActivity</h1>
 * Acitvity responsavel por implementar a funcionalidade editar perfil.
 */
public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private UserBusiness userBusiness = new UserBusiness();
    private Bitmap selectedImage;
    private static final int REQUEST_GALERY = 0;
    private static final int REQUEST_CROP = 1;


    /**
     * O metodo onCreate() seta o layout: activity_edit_profile e setar os
     * EditTexts, Buttons e o CircleImageView do layout para cada atributo da classe e chamar o metodo
     * checkSession() e showUserLoggedData da mesma classe.
     * @see EditProfileActivity#showUserLoggedData()
     * @see EditProfileActivity#checkSession()
     * @param savedInstanceState Um objeto da classe Bundle que contem o estado anterior da activity
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        this.mViewHolder.btnSave = findViewById(R.id.EditProfileBtnSave);
        this.mViewHolder.imgProfile = findViewById(R.id.ImgProfile);
        this.mViewHolder.edtEmail = findViewById(R.id.EditProfileEmail);
        this.mViewHolder.edtName = findViewById(R.id.EditProfileName);
        this.mViewHolder.imgProfile.setOnClickListener(this);
        this.mViewHolder.btnSave.setOnClickListener(this);
        checkSession();
        showUserLoggedData();
    }
    /**
     *O metodo checkSession() recupera o usuario da sessao
     */
    public void checkSession(){
        if (Session.getUserIn() == null){
            new UserBusiness().recoverSession();
        }
    }

    /**
     * O metodo showUserLoggedData() mostra na tela o nome, email e foto usuario, recuperando dados pela sessao
     */
    public void showUserLoggedData(){
        this.mViewHolder.edtName.setText(Session.getUserIn().getUserName());
        this.mViewHolder.edtEmail.setText(Session.getUserIn().getUserEmail());
        if (Session.getUserIn().getUserPhoto()!=null){
            this.mViewHolder.imgProfile.setImageBitmap(Session.getUserIn().getUserPhoto());
            selectedImage = Session.getUserIn().getUserPhoto();
        }
    }

    /**
     * O metodo onClick() recebe o id recebido pela view e compara com o id do botao salvar e do CircleImageView,
     * caso seja o do botao salvar ele emite um toast de confirmacao e direciona a pagina de perfil, se a
     * CircleImageView for clicada voce e direcionado para a galeria do celular
     * @param view Recebe o que foi observado na view
     */
    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.EditProfileBtnSave){
            if (verifyEdition()){
                Toast.makeText(this,R.string.toastUpdateSuccessful,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        }
        if (id==R.id.ImgProfile){
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_GALERY);
        }
    }

    /**
     * O metodo onActivityResult() é responsavel por cortar a imagem do tamanho correto
     * @param requestCode O código de pedido passado para startActivityForResult().
     * @param resultCode Um código de resultado especificado pela segunda atividade. Se a operação
     *                   for bem-sucedida, isso será RESULT_OK. E será RESULT_CANCELED se o
     *                   usuário tiver desistido ou a operação tiver falhado por algum motivo.
     * @param data Um Intent que transporta os dados do resultado.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALERY && resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            final Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(targetUri, "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 128);
            intent.putExtra("outputY", 128);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, REQUEST_CROP);
        } else if (requestCode == REQUEST_CROP && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            if (extras!=null){
                Bitmap bitmap = extras.getParcelable("data");
                selectedImage = bitmap;
                mViewHolder.imgProfile.setImageBitmap(bitmap);
            }

        }
    }

    /**
     * No metodo verifyEdition() caso o metodo validateFields retorne true, verifica se o email atualizado e
     * o mesmo email ja inserido no sistema sao iguais. Caso o email seja igual, ele altera a o nome e a foto
     * @return Retorna true se a alteracao tiver sido concluido com sucesso, caso nao tenha sido alterada com
     * sucesso, retorna falso
     */

    public boolean verifyEdition(){
        String name = this.mViewHolder.edtName.getText().toString().trim();
        String email = this.mViewHolder.edtEmail.getText().toString().trim();
        if (validateFields()){
            if (!userBusiness.updateUserEmail(email)){
                this.mViewHolder.edtEmail.setError("Email já cadastrado");
                return false;
            } else{
                userBusiness.updateUserPhoto(selectedImage);
                userBusiness.updateUserName(name);
                return true;
            }
        }
        return false;
    }

    /**
     * O metodo validateFields() verifica se os campos estao vazios e no caso do email utiliza o metodo
     * regexEmail da classe UserBusiness, se os campos estiverem neste padrao ele retorna true
     * @return  Retorna true se os campos estiverem de acordo com as exigencias.
     */
    public boolean validateFields(){
        String name = this.mViewHolder.edtName.getText().toString().trim();
        String email = this.mViewHolder.edtEmail.getText().toString().trim();

        boolean blankValidate = true;
        if (TextUtils.isEmpty(name) || name.length() < 4){
            this.mViewHolder.edtName.setError("O nome deve conter 4 ou mais caracteres");
            blankValidate = false;
        }
        if (TextUtils.isEmpty(email) || !userBusiness.regexEmail(email)){
            this.mViewHolder.edtEmail.setError("Email invalido");
            blankValidate = false;
        }
        return blankValidate;
    }

    private static class ViewHolder{
        private Button btnSave;
        private CircleImageView imgProfile;
        private EditText edtName;
        private EditText edtEmail;
    }

    /**
     * Metodo utiliza o intent para ir para a ProfileActivity
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        UserBusiness userBusiness = new UserBusiness();
        userBusiness.recoverSession();
    }
}

