package com.example.RestAPI.Service;

import com.example.RestAPI.Model.FipeEntity;
import com.example.RestAPI.Repository.FipeRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class FipeService {

    @Autowired
    private FipeRepository fipeRepository;

    public List<FipeEntity> obterTodos(){
        return fipeRepository.findAll();
    }

    public FipeEntity obterPorId(String id){
        return fipeRepository.findById(id).orElse(null);
    }

    public FipeEntity inserir(FipeEntity fipe){
        return fipeRepository.save(fipe);
    }

    public FipeEntity atualizar(String id, FipeEntity newFipe){
        FipeEntity existingFipe = fipeRepository.findById(id).orElse(null);

        if(existingFipe != null){
            existingFipe.setTipoVeiculo(newFipe.getTipoVeiculo());
            existingFipe.setValor(newFipe.getValor());
            existingFipe.setMarca(newFipe.getMarca());
            existingFipe.setModelo(newFipe.getModelo());
            existingFipe.setAnoModelo(newFipe.getAnoModelo());
            existingFipe.setCombustivel(newFipe.getCombustivel());
            existingFipe.setCodigoFipe(newFipe.getCodigoFipe());
            existingFipe.setMesReferencia(newFipe.getMesReferencia());
            existingFipe.setSiglaCombustivel(newFipe.getSiglaCombustivel());
            return fipeRepository.save(existingFipe);
        }
        else{
            return null;
        }
    }

    public void excluir(String id){
        fipeRepository.deleteById(id);
    }

    public String obterInformacoesFipe() {
        String dadosFipe;
        String apiUrl = "https://parallelum.com.br/fipe/api/v1/carros/marcas/59/modelos/5940/anos/2014-3";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            dadosFipe = responseEntity.getBody();

            try {
                JSONObject jsonObject = new JSONObject(dadosFipe);

                FipeEntity fipe = new FipeEntity();
                fipe.setTipoVeiculo(jsonObject.getInt("TipoVeiculo"));
                fipe.setValor(jsonObject.getString("Valor"));
                fipe.setMarca(jsonObject.getString("Marca"));
                fipe.setModelo(jsonObject.getString("Modelo"));
                fipe.setAnoModelo(jsonObject.getInt("AnoModelo"));
                fipe.setCombustivel(jsonObject.getString("Combustivel"));
                fipe.setCodigoFipe(jsonObject.getString("CodigoFipe"));
                fipe.setMesReferencia(jsonObject.getString("MesReferencia"));
                fipe.setSiglaCombustivel(jsonObject.getString("SiglaCombustivel"));

                fipeRepository.save(fipe);

            } catch (JSONException e) {
                dadosFipe = "Erro ao analisar os dados da API da FIPE.";
                e.printStackTrace();
            }
        }
        else {
            dadosFipe = "Falha ao obter dados da API da FIPE. Código de status: " + responseEntity.getStatusCode();
        }

        return dadosFipe;
    }
}
