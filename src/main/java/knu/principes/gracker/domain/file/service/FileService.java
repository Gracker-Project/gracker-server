package knu.principes.gracker.domain.file.service;


import knu.principes.gracker.domain.subject.dto.RequestSubjectDto;
import knu.principes.gracker.domain.subject.dto.ResponseSubjectDto;
import knu.principes.gracker.domain.subject.entity.Subject;
import knu.principes.gracker.domain.subject.service.SubjectService;
import knu.principes.gracker.global.config.ClovaOCRConfig;
import knu.principes.gracker.global.config.OpenAiConfig;
import knu.principes.gracker.global.exception.OCRProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FileService {


}
