# Interface Spring Boot Security JWT - Json-Web-Token usando Java 21 com Postman e PostgreSQL17.

Nota: Ao executar a classe PasswordEncoderUtil de forma independente, você pode obter o código criptografado que será salvo no banco de dados.

## Table Users

![/Table Users](Images/Table_PostgreSQL/Table_users.png)

## Table Roles

![/Table Users](Images/Table_PostgreSQL/Table_roles.png)

## Table Users- Roles

![/Table Users](Images/Table_PostgreSQL/Table_user_roles.png)


## Acesso público do Postman

![/public](Images/Pruebas_Postman/Aceso_Libre_Sem_Senha.png)

# Para usar o cookie de sessão em solicitações subsequentes no Postman para identificar o usuário autenticado, siga estas etapas:

## Etapa 1: Certifique-se de que o Postman esteja configurado para salvar cookies automaticamente.

Por padrão, o Postman deve salvar os cookies recebidos do servidor e enviá-los automaticamente em solicitações subsequentes para o mesmo domínio. No entanto, 
É bom verificar esta configuração:

Abra as configurações do Postman: vá para Postman > Configurações (ou Arquivo > Configurações em algumas versões).
Selecione a aba "Geral".
Procure a seção "Cookies".
Certifique-se de que a opção "Enviar cookies automaticamente" esteja habilitada (marcada). Caso não esteja, ative-o.
Feche a janela de configuração.

## Etapa 2: faça a solicitação de login com sucesso.

Siga as etapas mencionadas acima para enviar uma solicitação POST ao ponto de extremidade de login do seu aplicativo com o nome de usuário e a senha corretos no corpo (x-www-form-urlencoded).
Se o login for bem-sucedido, o servidor responderá com um código de status 500 Found (redirecionamento) e definirá um ou mais cookies no cabeçalho Set-Cookie da resposta. Um desses biscoitos 
Este será o cookie de sessão (por exemplo, JSESSIONID no Tomcat).

O Postman deve salvar este cookie automaticamente. Você pode verificar isso da seguinte maneira:
Após receber a resposta de login, clique na aba "Cookies" logo abaixo da barra de URL no Postman.
Você deverá ver o cookie de sessão listado para o domínio do seu aplicativo.

# Observação: A solicitação POST no login me permite enviar meu nome de usuário e senha para liberar o acesso e, em seguida, fazer solicitações, se autorizadas, usando o método GET.

## Habilitar admin

![/login para admin](Images/Pruebas_Postman/Postman_POST_em_login_para_liberar_acesso-ROLE_ADMIN-com-clave-valor-certo.png)

## Habilitar user
![/login para user](Images/Pruebas_Postman/Postman_POST_em_login_para_liberar_acesso_com-clave-valor-certo.png)


## Etapa 3: Faça solicitações subsequentes aos recursos protegidos.

Crie uma nova solicitação no Postman para o recurso protegido que você deseja acessar (por exemplo, uma solicitação GET para http://localhost:8080/admin ou http://localhost:8080/user).
Certifique-se de que o URL da solicitação corresponda ao domínio para o qual o cookie de sessão foi definido.
Você não precisa adicionar manualmente o cookie aos cabeçalhos. O Postman deve enviar automaticamente os cookies relevantes (incluindo o cookie de sessão) salvos do servidor durante o login.
Clique em "Enviar" para enviar a solicitação.

## Interpretação da Resposta:

Acesso permitido (Código 200 OK): Se o cookie de sessão for válido e o usuário autenticado tiver as funções necessárias, o servidor deverá responder com o recurso solicitado.

## Acesso permitido para admin

![/Acesso permitido para admin](Images/Pruebas_Postman/Postman_GET_Liberado_Acesso_tem_ROLE_ADMIN.png)

##Acesso permitido para user

![/Acesso permitido para user](Images/Pruebas_Postman/Postman_GET_Liberado_Acesso_tem_ROLE_USER.png)


Acesso negado (Código 403 proibido): se o cookie for válido, mas o usuário não tiver as funções necessárias, você receberá um erro de acesso negado.
Não autenticado (redirecionamento para login - código 302 ou 401 não autorizado): se o cookie de sessão não foi enviado (por algum motivo) ou expirou, o servidor o redirecionará novamente 
para a página de login ou retornará um erro não autenticado.

## Usuário com rol diferente

![/Rol diferente](Images/Pruebas_Postman/Role_Diferente.png)

## O usuário não existe

![/no existe](Images/Pruebas_Postman/Usuario_nao_existe.png)








