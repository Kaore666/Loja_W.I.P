<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>

<html lang="pt-br">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>FoxShop - Form Produto</title>
        <link rel="icon" type="image/x-icon" href="assets/img/icon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <link href="css/glyphicon.css" media="all" rel="stylesheet" type="text/css"/>
        <link href="css/fileinput.min.css" media="all" rel="stylesheet" type="text/css"/>
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body id="page-top">
        <!-- Navigation-->
        <tag:menu-superior>
        
        </tag:menu-superior>
        <!-- Masthead-->
        <header class="masthead" id="login">
            <div class="container">
               
            </div>
        </header>
        <!-- formproduto-->
        <section class="page-section" id="formproduto">
            <div class="container">
                <div class="text-center">
                <c:if test="${produto.id == null}">
                
                	<h2 class="section-heading text-uppercase">Novo Produto</h2>
                
                </c:if>
                
                <c:if test="${produto.id != null}">
                
                	<h2 class="section-heading text-uppercase">Editar Produto</h2>
                
                </c:if>
    
                </div>
                
                <c:if test="${not empty errors}">
                
                <div class="alert alert-danger" role="alert">
                	<c:forEach var="error" items="${errors}">
                		${error.message}<br />
                		
                	</c:forEach>
                </div>
                
                </c:if>
                
                <form method="post" action="<c:url value="/formproduto/salvaProduto"/>" enctype="multipart/form-data">
                <input type="Hidden" name="produto.id" value="${produto.getId()}"/>
                <input type="Hidden" name="produto.ativo" value="${produto.isAtivo()}"/>
                    <div class="row justify-content-md-center mb-5 text-center">
                        <div class="col-md-12 align-self-center text-center">
                            <div class="form-group input-login mx-auto">
                                <input name="produto.imagem.file" id="input-id" type="file" class="file" data-preview-file-type="text" required="required">
                                 <p class="help-block text-danger"></p>
                             </div>
                            <div class="form-group input-login mx-auto">
                            
                                <input class="form-control" id="nome" name="produto.nome" value="${produto.getNome()}" type="text" minlength="3" maxlength="60" placeholder="Nome" required="required" data-validation-required-message="Please."/>
                                
                                <p class="help-block text-danger"></p>
                            </div>
                            
                            <div class="form-group input-login mx-auto">
                            
                                <input class="form-control money" id="valor" name="produto.valor" value="${produto.getValor()}" type="text" placeholder="Valor em R$" required="required" data-validation-required-message="Please."/>                            
                                
                                <p class="help-block text-danger"></p>
                            </div>
                            
                            <div class="form-group input-login mx-auto">
                            
                               <textarea class="form-control" name="produto.desc" required="required" placeholder="Descreva o Produto" data-validation-required-message="Please.">${produto.getDesc()}</textarea>
                               
                                <p class="help-block text-danger"></p>
                            </div>
                            
                            <div class="form-group">
                                <select name="produto.categorias.id" class="form-control input-login mx-auto" id="categoria" required="required" data-validation-required-message="Please.">
                                
                                	<option value="" disabled selected>Selecione uma categoria</option>
                                
	                                <c:forEach var="categoria" items="${categorias}">
	                                
	                                  <option value="${categoria.id}">${categoria.nomeCateg}</option>
	                                
	                                </c:forEach>
                                  
                                </select>
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group input-login mx-auto">
                                <input class="form-control date-br" id="data"name="produto.dataValidadeEn" value="${produto.dataValidade}" type="date" placeholder="Data Validade" required="required" data-validation-required-message="Please."/>
                                <p class="help-block text-danger"></p>
                            </div>
                           
                             <button type= "submit" class="btn btn-primary btn-xl text-uppercase js-scroll-trigger">Salvar</button>
                        </div> 
                    </div>
                </form>
            </div>
        </section>
    
        <!-- Footer-->
        <tag:barra-inferior>
        
        </tag:barra-inferior>
        <!-- Bootstrap core JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Third party plugin JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
        <!-- Contact form JS-->
        <script src="assets/mail/jqBootstrapValidation.js"></script>
        <script src="assets/mail/contact_me.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        
        <script src="js/jquery.mask.min.js"></script>
        <script src="js/fileinput/fileinput.min.js" type="text/javascript"></script>
        <script src="js/add-mask.js"></script>
        <script>

        </script>
    </body>
</html>
